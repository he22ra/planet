import React from 'react';
import axios from 'axios';
// import Movie from "./Movie";
import MovieTest from "./MovieTest";

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

    const dates = Year+"년 "+Month+"월 "+Day+"일";
    this.setState({ movies: dailyBoxOfficeList, isLoading : false, dates});

    // KMDB poster, plot
    dailyBoxOfficeList.forEach(movie => {
      this.getPostersAndPlot(movie.movieNm, movie.openDt);
    });

  }

  getPostersAndPlot = async (movieTitle, releaseDts) => {
    let ServiceKey = 'LZ9S389G2U0IY99S0822';
    try {
      movieTitle = movieTitle.split('(')[0];
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
    return <div className="p-3 flex-row justify-content-center bg-light" id="main-container" style={{ width: "1000px", margin: "0 auto" }}>
              <div id="logo">LOGO TITLE</div>
              <div className="fst-italic fw-bold mb-3">{dates} 박스오피스 10</div>
              <div id="header wrap" className="row">
                <div id="title" className="col fs-5 fw-bold mb-3">어제의 랭킹</div>
              </div>
              <div className="row g-3">
                {isLoading
                  ? 'Loading...'
                  : movies.map((movie) => {
                    const poster = posters[movie.movieNm];
                    const plot = plots[movie.movieNm];
                    
                    
                    return(
                      <MovieTest 
                      key={movie.movieCd}
                      rank={Number(movie.rank)}
                      movieCd={movie.movieCd}
                      openDt={movie.openDt}
                      movieNm={movie.movieNm}
                      audiCnt={movie.audiCnt}
                      poster={poster}
                      plot={plot}
                      />
                    ); 
                  })}
              </div>
            </div>;
  }
}


export default App;
