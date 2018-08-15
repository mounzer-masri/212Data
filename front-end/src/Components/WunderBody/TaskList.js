import React, { Component, Fragment } from "react";
import {
  Button,
  List,
  ListItem,
  ListItemIcon,
  ListItemText
} from "material-ui";
import { ListItemSecondaryAction, Checkbox } from "material-ui";
import { Paper, Tabs } from "material-ui";
import { Tab } from "material-ui/Tabs";
import axios from "axios";
import CreateTask from "./Dialogs/CreateTask";

class TaskList extends Component {
  state = {
    myData: []
  };
  constructor(props) {
    super(props);
  }

  changeTaskStatus(taskId, event) {
    console.log(event.target.checked);

    var isCompleted = event.target.checked;
    var operationName = "";
    if (isCompleted) {
      operationName = "markTaskAsCompleted";
    } else {
      operationName = "markTaskAsNotCompleted";
    }
    // console.log(catId);
    var self = this;
    let token = sessionStorage.getItem("access-token");
    var config = {
      headers: { "access-token": token }
    };
    var objs = this.props.parentContext.state.currentTaskList;
    var apiBaseUrl = "http://localhost:8080";
    axios
      .get(apiBaseUrl + "/tasks/" + taskId + "/" + operationName + "/", config)
      .then(function(response) {
        console.log(response.status);
        console.log(response.data); // ex.: 200
        objs.map((obj, index) => {
          if (obj.id == taskId) {
            //obj.isCompleted = !isCompleted;
          }
        });
        //self.setState({ taskList: response.data });
      });
  }
  render() {
    console.log("parent");
    console.log(this.props.parentContext.state.currentTaskList);
    var objs = this.props.parentContext.state.currentTaskList; //[]; // self.props.parentContext.s;
    return (
      <div>
        <h3>Task List : Name / Note</h3>
        <List>
          {objs.map((obj, index) => {
            return (
              <ListItem key={obj.id} dense button>
                <ListItemText primary={obj.name} />
                <ListItemText primary={obj.note} />
              </ListItem>
            );
          })}
        </List>
        <CreateTask parentContext={this.props.parentContext} />
      </div>
    );
  }
}

export default TaskList;
