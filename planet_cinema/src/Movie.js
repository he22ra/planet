import React from "react";
import PropTypes from 'prop-types';

function Movie({rank, movieCd, openDt, movieNm, audiCnt, poster, plot}) {
  return(
    <div class="w-75">
      {/* rank 1~3 */}
      <div id="top-rank" class="row">
        <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="false">
          <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
          </div>
          <div class="carousel-inner">
            <div class="carousel-item active">
              {/* poster start */}
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
            {/* poster end */}
              <div class="carousel-caption d-none d-md-block">
                <h5>First slide label</h5>
                <p>Some representative placeholder content for the first slide.</p>
              </div>
            </div>
            <div class="carousel-item">

              <div class="carousel-caption d-none d-md-block">
                <h5>Second slide label</h5>
                <p>Some representative placeholder content for the second slide.</p>
              </div>
            </div>
            <div class="carousel-item">
              <div class="carousel-caption d-none d-md-block">
                <h5>Third slide label</h5>
                <p>Some representative placeholder content for the third slide.</p>
              </div>
            </div>
          </div>
          <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>
      </div>
      {/* rank 4~10 */}
      <div id="sub-rank" class="row">
        <div id="rank" class="border col-1 fs-3 fw-bold ">{rank}</div>
        <div id="poster" class="border col-1">
           {/* poster start */}
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
            {/* poster end */}
        </div>
        <div id="movieTitle" class="border col">{movieNm}</div>
        <div id="movieinfoMore" class="border col-1">더보기</div>
      </div>
      {/* <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
       
        <div class="col p-4 d-flex flex-column position-static">
          <strong class="d-inline-block mb-2"><span class=" badge text-bg-primary"></span> <span class="mb-2 fw-bold">{movieNm}</span> </strong> 
          <div class="row justify-content-end mb-1 text-body-secondary small">
            <div class="col">개봉일 : {openDt ? (openDt) : ("정보없음")}</div>
            <div class="col justify-content-end">관객수 : {audiCnt}명</div>
          </div>
          <p class="form-text mb-auto d-inline-block plot-text">{plot}</p>

        </div>
      </div> */}
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
