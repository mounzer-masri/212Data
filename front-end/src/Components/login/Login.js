import React, { Component, Fragment } from "react";
import { Button, Checkbox } from "material-ui";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import AppBar from "material-ui/AppBar";
//import RaisedButton from 'material-ui';
import WunderBody from "../WunderBody/index";
import TextField from "material-ui/TextField";
import axios from "axios";

class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: "",
      chk: true
    };
  }
  handleChange = name => event => {
    this.setState({ chk: event.target.checked });
  };
  handleClick(event) {
    var apiBaseUrl = "http://localhost:8080";
    var self = this;
    console.log(this);
    var payload = {
      email: this.state.username,
      password: this.state.password
    };

    axios
      .post(apiBaseUrl + "/login/", payload)
      .then(function(response) {
        console.log(response);
        if (response.status == 200) {
          sessionStorage.setItem("access-token", response.data["token"]);
          var uploadScreen = [];
          uploadScreen.push(<WunderBody appContext={self.props.appContext} />);
          self.props.appContext.setState({
            loginPage: [],
            uploadScreen: uploadScreen
          });
        } else if (response.status == 204) {
          console.log("Username password do not match");
          alert("username password do not match");
        } else {
          console.log("Username does not exists");
          alert("Username does not exist");
        }
      })
      .catch(function(error) {
        console.log(error);
      });
  }

  render() {
    return (
      <Fragment>
        <TextField
          autoFocus
          id="email"
          label="Email :"
          onChange={event => this.setState({ username: event.target.value })}
        />
        <br />
        <TextField
          autoFocus
          id="password"
          type="password"
          label="Password :"
          onChange={event => this.setState({ password: event.target.value })}
        />
        <br /> <br />
        <Button onClick={event => this.handleClick(event)} color="primary">
          Login
        </Button>
      </Fragment>
    );
  }
}
const style = {
  margin: 15
};
export default Login;
