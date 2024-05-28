const axios = require('axios');
const cheerio = require('cheerio');

const url = 'https://kobis.or.kr/kobis/business/mast/mvie/searchMovieList.do?dtTp=movie&dtCd=20212932';

axios.get(url)
    .then(response => {
        const html = response.data;
        const $ = cheerio.load(html);
        const movieThumbnails = [];

        // Iterate through elements with the class 'fl thumb'
        $('.fl thumb').each((index, element) => {
            const thumbUrl = $(element).find('img').attr('src');
            movieThumbnails.push(thumbUrl);
        });

        console.log(movieThumbnails);
    })
    .catch(error => {
        console.error(`There was an error fetching the data: ${error}`);
    });
