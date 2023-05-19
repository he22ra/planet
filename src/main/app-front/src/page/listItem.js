/**
 * ListItem 게시판보드에 들어가는 개별적인 데이터들
 */
import React from 'react';
import { Link } from 'react-router-dom';
import './ListItem.css'

function ListItem({ id, title, writer, created_date, modified_date }) {

    return (
        <Link to={{
            pathname:"/detail",
            search:`?id=${id}`
        }} style={{ textDecoration: 'none', color: 'black'}}>
            <div className="list-item">
                <div className="id">{id}</div>
                <div className="title">{title}</div>
                <div className="writer">{writer}</div>
                <div className="created_date">{created_date}</div>
            </div>
        </Link>
    )
}

export default ListItem;