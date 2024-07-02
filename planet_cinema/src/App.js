import React from 'react';
import axios from 'axios';
import Movie from "./Movie";

class App extends React.Component {
  state = {
    isLoading: true,
    movies: [],
    dates: [],
    posters: {},
    plots: {},
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

    // KMDB poster, plot
    dailyBoxOfficeList.forEach(movie => {
      this.getPostersAndPlot(movie.movieNm, movie.openDt);
    });

  }

  getPostersAndPlot = async (movieTitle, releaseDts) => {
    let ServiceKey = 'LZ9S389G2U0IY99S0822';
    try {
      movieTitle = movieTitle.split('(');
      const response = await axios.get(
        "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp?collection=kmdb_new2&detail=Y" +
          "&title=" + movieTitle +
          "&releaseDts=" + releaseDts +
          "&ServiceKey=" + ServiceKey
      );
      const result = response.data.Data[0].Result[0];
      const posterData = result.posters.split('|')[0];
      const plotData = result.plots.plot[0].plotText;

      this.setState(prevState => ({
        posters: {
          ...prevState.posters,
          [movieTitle]: posterData,
        },
        plots: {
          ...prevState.plots,
          [movieTitle]: plotData,
        },
      }));
    } catch (error) {
      console.error("Error poster :", movieTitle, error);
    }
  };
  
  
  componentDidMount() {
    // 영화 데이터 로딩!
    this.getMovies();
    this.getPostersAndPlot();
  }

  render() {
    const {isLoading, movies, dates, posters, plots} = this.state;
    return <div class="p-3">
              <h2 class="fst-italic fw-bold mb-3">{dates} 박스오피스 10</h2>
              {isLoading
                ? 'Loading...'
                : movies.map((movie) => {
                  const poster = posters[movie.movieNm];
                  const plot = plots[movie.movieNm];
                  
                  return(
                    <Movie 
                    key={movie.movieCd}
                    rank={movie.rank}
                    movieCd={movie.movieCd}
                    openDt={movie.openDt}
                    movieNm={movie.movieNm}
                    audiCnt={movie.audiCnt}
                    poster={poster}
                    plot={plot}
                    />
                  ); 
                })}
            </div>;
  }
}


export default App;
