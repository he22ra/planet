import React from "react";
import PropTypes from 'prop-types';

function Movie({rank, movieCd, openDt, movieNm, audiCnt, poster}) {
  return(
    <div>
      {poster && <img src={poster} alt={`${movieNm} poster`} />}
      <h4>{rank}위 : {movieNm}</h4>
    </div>
  );
}

Movie.propTypes = {
  rank: PropTypes.string.isRequired,
  movieCd : PropTypes.string.isRequired,
  openDt : PropTypes.string.isRequired,
  movieNm : PropTypes.string.isRequired,
  audiCnt : PropTypes.string.isRequired,
  poster: PropTypes.string,  // Add poster as an optional prop
};

export default Movie;
