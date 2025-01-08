import React, { useState } from "react";
import PropTypes from "prop-types";

function MovieTest({ rank, movieCd, openDt, movieNm, audiCnt, poster, plot, url }) {
  const [isOpen, setIsOpen] = useState(false);
  const handleAccordionToggle = () => {
    setIsOpen(!isOpen); // 아코디언 open or close
  };

  const renderCarousel = () => (
    <div className="col mb-3">
      <div className="card shadow-sm">
        <div className="card-body">
          <p className="card-text d-flex justify-content-center my-1">
            <div id="next" className="col"> <span className="badge text-wrap"> {rank}위 </span></div> 
            <h5 className="fw-bold">{movieNm}</h5>
          </p>
          <p className="moviePoster">
          {poster ? (
            <img
              src={poster}
              alt={`${movieNm} poster`}
              className="bd-placeholder-img card-img-top"
            />
          ) : (
            <svg
              className="bd-placeholder-img card-img-top"
              width="213"
              xmlns="http://www.w3.org/2000/svg"
              role="img"
              aria-label="Placeholder: Thumbnail"
              preserveAspectRatio="xMidYMid slice"
              focusable="false"
              height="304"
            >
              <title>Placeholder</title>
              <rect width="100%" height="100%" fill="#eee"></rect>
              <text x="31%" y="50%" fill="#666" dy=".2em">
                Thumbnail
              </text>
            </svg>
          )}
          </p>
          <div className="d-flex justify-content-between align-items-center">
            <small className="text-muted">개봉일: {openDt}</small>
            <div className="btn-group">
              <a href={url} target="_blank"><button type="button" className="btn btn-sm btn-outline-secondary">더보기</button></a>
            </div>
          </div>
        </div>
      </div>
    </div>
  );

  return (
    <>
      {rank <= 3 ? (
        <div id="top-rank" className="col">
          {renderCarousel()}
        </div>
      ) : (
        <div className="accordion accordion-flush" id="accordionFlush">
          <div class="accordion-item">
            <h2 class="accordion-header" id={`flush-heading${rank}`}>
              <button id="sub-rank" type="button"
                className={`row accordion-button ${isOpen ? "" : "collapsed"}`}
                onClick={handleAccordionToggle} // 아코디언 열기
                aria-expanded={isOpen ? "true" : "false"} // 아코디언 열림/닫힘 상태 체크
                aria-controls={`flush-collapse${rank}`}
              >                
                <div id="rank" className="mb-2 col-1 fs-4 fw-bold text-center ">
                  {rank}
                </div>
                <div id="poster" className="mb-2 col-1">
                  {poster ? (
                    <img
                      src={poster}
                      alt={`${movieNm} poster`}
                      className="bd-placeholder-img card-img-top"
                    />
                  ) : (
                    <svg
                      className="bd-placeholder-img card-img-top"
                      width="213"
                      xmlns="http://www.w3.org/2000/svg"
                      role="img"
                      aria-label="Placeholder: Thumbnail"
                      preserveAspectRatio="xMidYMid slice"
                      focusable="false"
                      height="304"
                    >
                      <title>Placeholder</title>
                      <rect width="100%" height="100%" fill="#eee"></rect>
                      <text x="31%" y="50%" fill="#666" dy=".2em">
                        Thumbnail
                      </text>
                    </svg>
                  )}
                </div>
                <div id="movieTitle" className="mb-2 col">
                  {movieNm}
                </div>
                <div id="movieinfoOpenDt" className="mb-2 col-2 text-end p-0">
                  <small className="text-muted">개봉일: {openDt}</small>  
                </div>
                <div id="movieinfoMore" className="mb-2 col-1 text-end py-0 px-2">
                  <a href={url} target="_blank"><button type="button" className="btn btn-sm btn-outline-secondary">더보기</button></a>
                </div>
              </button>
            </h2>
            <div
              id={`flush-collapse${rank}`}
              className={`accordion-collapse collapse ${isOpen ? "show" : ""}`} // 아코디언 열림/닫힘 상태 체크
              aria-labelledby={`flush-heading${rank}`}
              data-bs-parent="#accordionFlush"
            >
              <div class="accordion-body">
                <strong>줄거리 | </strong> {plot}
              </div>
            </div>
          </div>
        </div>
        
      )}
      <style>{`
        #top-rank {
          width: 100%;
          min-height: 140px;
          overflow: hidden;
        }
        #rank {color: #6f42c1;}
        .badge {background-color: #6f42c1;}
        .carousel-item img {
          height: 140px;
          object-fit: cover;
        }
        .moviePoster img {
          height: 420px;
          width: 100%;
          object-fit: cover;
          object-position: center;
        }
        .moviePlot {width:200px;}
        .card {height: auto; width: 100%;}
        .card-text {max-width: 100%; max-height: 250px;} 
      `}</style>
    </>
  );
}

MovieTest.propTypes = {
  rank: PropTypes.number.isRequired,
  movieCd: PropTypes.string.isRequired,
  openDt: PropTypes.string.isRequired,
  movieNm: PropTypes.string.isRequired,
  audiCnt: PropTypes.string.isRequired,
  poster: PropTypes.string,
  plot: PropTypes.string,
  url: PropTypes.string,
};

export default MovieTest;
