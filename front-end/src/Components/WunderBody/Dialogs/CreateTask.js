import React, { Component, Fragment } from "react";
import { Dialog, Button, TextField } from "material-ui";
import AddIcon from "material-ui-icons/Add";
import {
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle
} from "material-ui/Dialog";
import axios from "axios";

export default class CreateCategory extends Component {
  state = {
    open: false,
    newTask: {
      name: "",
      note: "",
      dueDate: ""
    }
  };

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
      name: this.state.name,
      note: this.state.note,
      dueDate: this.state.dueDate
    };
    var config = {
      headers: { "access-token": token }
    };
    var selectedCategoryId = self.props.parentContext.state.selectedCategoryId;
    axios
      .post(
        apiBaseUrl + "/categories/" + selectedCategoryId + "/tasks/",
        payload,
        config
      )
      .then(function(response) {
        console.log(response);
        if (response.status == 201) {
          //TODO: refresh paarent status .
          self.props.parentContext.setState({
            currentTaskList: [
              ...self.props.parentContext.state.currentTaskList,
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
          <DialogTitle id="form-dialog-title">Add Task :</DialogTitle>

          <TextField
            autoFocus
            id="name"
            label="Name"
            onChange={event => this.setState({ name: event.target.value })}
          />
          <TextField
            autoFocus
            id="note"
            label="Note"
            multiline
            rows="4"
            onChange={event => this.setState({ note: event.target.value })}
          />
          <TextField
            id="dueDate"
            label="Ddue To :"
            type="date"
            defaultValue="2020-01-01"
            InputLabelProps={{
              shrink: true
            }}
            onChange={event => this.setState({ dueDate: event.target.value })}
          />

          <DialogActions>
            <Button onClick={this.handleClose} color="primary">
              Cancel
            </Button>
            <Button onClick={this.handleClick} color="primary">
              Add Task
            </Button>
          </DialogActions>
        </Dialog>
      </Fragment>
    );
  }
}
