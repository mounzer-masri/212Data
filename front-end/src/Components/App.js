import React, { Component, Fragment } from "react";
import { Heaader, Footer } from "./Layouts/";
import WunderBody from "./WunderBody/index";
import Loginscreen from "./login/Loginscreen";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      loginPage: [],
      uploadScreen: []
    };
  }
  componentWillMount() {
    var loginPage = [];
    loginPage.push(<Loginscreen appContext={this} />);
    this.setState({
      loginPage: loginPage
    });
  }
  render() {
    return (
      <div className="App">
        {this.state.loginPage}
        {this.state.uploadScreen}
      </div>
    );
  }
}
const style = {
  margin: 15
};
export default App;
