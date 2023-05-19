/**
 * list 게시판 보드
 */

import React, { useState, useEffect } from 'react';
import ListItem from './listItem';
import './list.css'

function useFetch(url) {

    const [data, setData] = useState([]);
    
    async function fetchUrl() {
        const response = await fetch(url);
        const json = await response.json();
        
        setData(json);
    }
    
    useEffect(() => {
        fetchUrl();
    }, []);
    return data;
}


function List() {

    const data = useFetch("/api/list");
    
    return (
        <main className="list-template">     
            <div className="list-title">
                폼 게시판
            </div>
            <section className="head-wrapper">
                <span>번호</span>
                <span className="title-column">제목</span>
                <span>작성자</span>
                <span>작성일</span>
                <span>마감일</span>
            </section>
            <section className="list-wrapper">
                {data.map(
                    ({board_id, title, start_date, end_date}) => (
                        <ListItem
                            board_id={board_id}
                            title={title}
                            start_date={start_date}
                            end_date={end_date}
                            key={board_id}
                        />
                    )
                )}
            </section>
        </main>
    );
}

export default List;