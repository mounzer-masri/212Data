import React, { Component, Fragment } from "react";
import { Button } from "material-ui";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import AppBar from "material-ui/AppBar";
//import RaisedButton from 'material-ui';
import TextField from "material-ui/TextField";
import Login from "./Login";
import axios from "axios";

class Register extends Component {
  state = {
    first_name: "",
    last_name: "",
    email: "",
    password: ""
  };

  constructor(props) {
    super(props);
  }
  handleClick(event) {
    var apiBaseUrl = "http://localhost:8080";
    console.log(
      "values",
      this.state.first_name,
      this.state.last_name,
      this.state.email,
      this.state.password
    );

    //To be done:check for empty values before hitting submit
    var self = this;
    var payload = {
      name: this.state.first_name,
      email: this.state.email,
      password: this.state.password
    };

    axios
      .post(apiBaseUrl + "/users/", payload)
      .then(function(response) {
        console.log(response);
        if (response.status == 201) {
          alert("registration successfull");
          console.log("registration successfull");
          var loginscreen = [];
          loginscreen.push(<Login parentContext={this} />);
          var loginmessage = "Not Registered yet.Go to registration";
          self.props.parentContext.setState({
            loginscreen: loginscreen,
            loginmessage: loginmessage,
            buttonLabel: "Register",
            isLogin: true
          });
        } else if (response.status == 409) {
          alert("Registration Failed, User Already Exists.");
        } else if (response.status == 422) {
          console.log("Registration Failed, Please Fill All Fields.");
          alert("Registration Failed, Please Fill All Fields.");
        }
      })
      .catch(function(error) {
        alert("Registration Failed");
        console.log(error);
      });
  }

  render() {
    return (
      <Fragment>
        <TextField
          autoFocus
          id="firstname"
          label="First Name :"
          onChange={event => this.setState({ first_name: event.target.value })}
        />
        <br />
        <TextField
          autoFocus
          id="lastname"
          label="Last Name :"
          onChange={event => this.setState({ last_name: event.target.value })}
        />
        <br />
        <TextField
          autoFocus
          id="email"
          label="Email :"
          onChange={event => this.setState({ email: event.target.value })}
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
          Register
        </Button>
      </Fragment>
    );
  }
}
const style = {
  margin: 15
};
export default Register;
