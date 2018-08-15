import React, { Component, Fragment } from "react";
import { Dialog, Button, TextField } from "material-ui";
import AddIcon from "material-ui-icons/Add";
import axios from "axios";

import {
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle
} from "material-ui/Dialog";

class CreateCategory extends Component {
  state = {
    open: false,
    categoryName: ""
  };
  constructor(props) {
    super(props);
  }
  handleClickOpen = () => {
    this.setState({ open: true });
  };

  handleClose = () => {
    this.setState({ open: false });
  };

  handleClick = () => {
    var apiBaseUrl = "http://localhost:8080";
    var self = this;
    let token = sessionStorage.getItem("access-token");
    var payload = {
      name: this.state.categoryName
    };
    var config = {
      headers: { "access-token": token }
    };
    axios
      .post(apiBaseUrl + "/categories/", payload, config)
      .then(function(response) {
        console.log(response);
        if (response.status == 201) {
          //TODO: refresh paarent status .
          self.props.parentContext.setState({
            categoryList: [
              ...self.props.parentContext.state.categoryList,
              response.data
            ]
          });
        } else {
          console.log("error");
          alert("error");
        }
      })
      .catch(function(error) {
        console.log(error);
      });

    this.setState({ open: false });
  };

  render() {
    return (
      <Fragment>
        <Button
          mini
          onClick={this.handleClickOpen}
          variant="fab"
          color="primary"
          aria-label="Add"
        >
          <AddIcon />
        </Button>

        <Dialog
          open={this.state.open}
          onClose={this.handleClose}
          aria-labelledby="form-dialog-title"
        >
          <DialogTitle id="form-dialog-title">Add Category :</DialogTitle>

          <TextField
            autoFocus
            id="name"
            label="name"
            onChange={event =>
              this.setState({ categoryName: event.target.value })
            }
          />
          <DialogActions>
            <Button onClick={this.handleClose} color="primary">
              Cancel
            </Button>
            <Button onClick={this.handleClick} color="primary">
              Add Category
            </Button>
          </DialogActions>
        </Dialog>
      </Fragment>
    );
  }
}
export default CreateCategory;
