/**
 * 2023.05.19 
 * https://dsc-sookmyung.tistory.com/36
 */

import React, { Component } from 'react';
import Main from './page/main';
import Write from './page/write';
import './App.css';
import { Route } from 'react-router-dom';

import logo from './logo.svg';

class App extends Component {

  render() {
    return(
      <div>
          <Route exact path="/" component={Main} />
          <Route path="/write" component={Write} />
      </div>
    )
  }
}
export default App;
