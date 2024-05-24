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
  }

  componentDidMount() {
    // 영화 데이터 로딩!
    this.getMovies();
  }

  render() {
    const {isLoading, movies, dates} = this.state;
    return <div>
              <h1>{dates} 박스오피스 10</h1>
              {isLoading
                ? 'Loading...'
                : movies.map((movie) => {
                  console.log(movie);
                  return(
                    <Movie 
                    key={movie.movieCd}
                    movieCd={movie.movieCd}
                    openDt={movie.openDt}
                    movieNm={movie.movieNm}
                    audiCnt={movie.audiCnt}
                    />
                  ); 
                })}
            </div>;
  }
}


export default App;
