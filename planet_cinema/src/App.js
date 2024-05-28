import React from 'react';
import axios from 'axios';
import Movie from "./Movie";

// import PropTypes from 'prop-types';
// import logo from './logo.svg';
// import './App.css';

class App extends React.Component {
  state = {
    isLoading: true,
    movies: [],
    dates: [],
    posters: {},
  };
  getMovies = async () => {
    var today = new Date();
    var yesterday = new Date(today.setDate(today.getDate() -1));
    var Year = yesterday.getFullYear();
    var Month = yesterday.toISOString().split('-')[1];
    var Day = yesterday.toDateString().split(' ')[2];
    let client_key = 'b2a08ed6c5c334ffeafc63ce4ab830bd';

    const {
     data : {
      boxOfficeResult : {dailyBoxOfficeList},
     }, 
    }  = await axios.get(
      "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key="
      +client_key+"&targetDt="+Year+Month+Day
    );
    const dates = yesterday.toISOString().replace('T', ' ').split(' ')[0];
    this.setState({ movies: dailyBoxOfficeList, isLoading : false, dates});

    // Fetch posters for each movie
    dailyBoxOfficeList.forEach(movie => {
      this.getPosters(movie.movieNm, movie.openDt);
    });

  }

  getPosters = async (movieTitle, releaseDts) => {
    let ServiceKey = 'LZ9S389G2U0IY99S0822';
    try {
      const response = await axios.get(
        "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp?collection=kmdb_new2&detail=Y" +
          "&title=" + movieTitle +
          "&releaseDts=" + releaseDts +
          "&ServiceKey=" + ServiceKey
      );
       // Extract poster URL
      const posterData = response.data.Data[0].Result[0].posters.split('|')[0]; // Use the first poster in the list
      console.log(posterData);
      this.setState(prevState => ({
        posters: {
          ...prevState.posters,
          [movieTitle]: posterData
        }
      }));
    } catch (error) {
      console.error("Error fetching poster for movie:", movieTitle, error);
    }
  };
  
  
  componentDidMount() {
    // 영화 데이터 로딩!
    this.getMovies();
    this.getPosters();
  }

  render() {
    const {isLoading, movies, dates, posters} = this.state;
    return <div>
              <h1>{dates} 박스오피스 10</h1>
              {isLoading
                ? 'Loading...'
                : movies.map((movie) => {
                  const poster = posters[movie.movieNm];
                  return(
                    <Movie 
                    key={movie.movieCd}
                    rank={movie.rank}
                    movieCd={movie.movieCd}
                    openDt={movie.openDt}
                    movieNm={movie.movieNm}
                    audiCnt={movie.audiCnt}
                    poster={poster}
                    />
                  ); 
                })}
            </div>;
  }
}


export default App;
