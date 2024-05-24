import React from "react";
import PropTypes from 'prop-types';

function Movie({movieCd, openDt, movieNm, audiCnt}) {
  return(
    <h4>{movieNm}</h4>
  );
}

Movie.propTypes = {
  movieCd : PropTypes.string.isRequired,
  openDt : PropTypes.string.isRequired,
  movieNm : PropTypes.string.isRequired,
  audiCnt : PropTypes.string.isRequired,
};

export default Movie;
