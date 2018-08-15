import React, { Component } from "react";
import Login from "./Login";
import Register from "./Register";
import { Button } from "material-ui";

class Loginscreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: "",
      loginscreen: [],
      loginmessage: "",
      buttonLabel: "Register",
      isLogin: true
    };
  }

  handleClick(event) {
    // console.log("event",event);
    var loginmessage;
    if (this.state.isLogin) {
      var loginscreen = [];
      loginscreen.push(
        <Register parentContext={this} appContext={this.props.appContext} />
      );
      loginmessage = "Already registered.Go to Login";
      this.setState({
        loginscreen: loginscreen,
        loginmessage: loginmessage,
        buttonLabel: "Login",
        isLogin: false
      });
    } else {
      var loginscreen = [];
      loginscreen.push(
        <Login parentContext={this} appContext={this.props.appContext} />
      );
      loginmessage = "Not Registered yet.Go to registration";
      this.setState({
        loginscreen: loginscreen,
        loginmessage: loginmessage,
        buttonLabel: "Register",
        isLogin: true
      });
    }
  }

  componentWillMount() {
    var loginscreen = [];
    loginscreen.push(
      <Login parentContext={this} appContext={this.props.appContext} />
    );
    var loginmessage = "Not registered yet, Register Now";
    this.setState({
      loginscreen: loginscreen,
      loginmessage: loginmessage
    });
  }
  render() {
    return (
      <div className="loginscreen">
        {this.state.loginscreen}
        <div>
          {this.state.loginmessage}
          <div>
            <Button
              label={this.state.buttonLabel}
              onClick={event => this.handleClick(event)}
              color="primary"
            >
              {this.state.buttonLabel}
            </Button>
          </div>
        </div>
      </div>
    );
  }
}
const style = {
  margin: 15
};
export default Loginscreen;
