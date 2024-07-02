import React from "react";
import PropTypes from 'prop-types';

function Movie({rank, movieCd, openDt, movieNm, audiCnt, poster, plot}) {
  return(
    <div class="col-md-6">
      <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        <div class="col p-4 d-flex flex-column position-static">
          <strong class="d-inline-block mb-2"><span class=" badge text-bg-primary">{rank}위</span></strong>
          <h4 class="mb-2 fw-bold">{movieNm}</h4>
          <div class="row justify-content-end mb-1 text-body-secondary small">
            <div class="col">{openDt ? (openDt) : ("정보없음")}</div>
            <div class="col">관객수 : {audiCnt}명</div>
          </div>
          <p class="form-text mb-auto d-inline-block plot-text">{plot}</p>
          <a href="#" class="icon-link gap-1 icon-link-hover stretched-link">
            더보기
          </a>
        </div>
        <div class="col-auto d-none d-lg-block">
          <div class="bd-placeholder-img" height="250" preserveAspectRatio="xMidYMid slice" focusable="false">
          {poster ? (
              <img src={poster} alt={`${movieNm} poster`} className="bd-placeholder-img card-img-top" />
            ) : (
              <svg className="bd-placeholder-img card-img-top" width="213" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false" height="304">
                <title>Placeholder</title>
                <rect width="100%" height="100%" fill="#eee"></rect>
                <text x="31%" y="50%" fill="#666" dy=".2em">Thumbnail</text>
              </svg>
            )}
          </div>
        </div>
      </div>
      <style jsx>{`
        .plot-text {
          max-height: 9em;
          overflow: hidden;
          text-overflow: ellipsis;
          display: -webkit-box;
          -webkit-line-clamp: 6;
          -webkit-box-orient: vertical;
          white-space: normal;
        }
      `}</style>
    </div>
  );
}

Movie.propTypes = {
  rank: PropTypes.string.isRequired,
  movieCd : PropTypes.string.isRequired,
  openDt : PropTypes.string.isRequired,
  movieNm : PropTypes.string.isRequired,
  audiCnt : PropTypes.string.isRequired,
  poster: PropTypes.string,
  plot: PropTypes.string, 
};

export default Movie;
