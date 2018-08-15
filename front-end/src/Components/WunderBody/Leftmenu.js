import React, { Component, Fragment } from "react";
import {
  Button,
  List,
  ListItem,
  ListItemIcon,
  ListItemText
} from "material-ui";
import AddIcon from "material-ui-icons/Add";
import CreateCategory from "./Dialogs/CreateCategory";
import axios from "axios";

class Leftmenu extends Component {
  state = {
    categoryList: []
  };
  constructor(props) {
    super(props);
    this.retrieveCategories();
  }

  retrieveCategories() {
    var self = this;
    let token = sessionStorage.getItem("access-token");
    var config = {
      headers: { "access-token": token }
    };

    var apiBaseUrl = "http://localhost:8080";
    axios.get(apiBaseUrl + "/categories/", config).then(function(response) {
      console.log(response.status); // ex.: 200
      self.setState({ categoryList: response.data });
    });
  }

  categoryDidSelected(catId, event) {
    console.log("ret tasks");
    console.log(catId);

    // console.log(catId);
    var self = this;
    let token = sessionStorage.getItem("access-token");
    var config = {
      headers: { "access-token": token }
    };

    var apiBaseUrl = "http://localhost:8080";
    axios
      .get(apiBaseUrl + "/categories/" + catId + "/tasks/", config)
      .then(function(response) {
        console.log(response.status);
        console.log(response.data); // ex.: 200
        self.props.parentContext.setState({
          currentTaskList: response.data,
          selectedCategoryId: catId
        });
        //self.setState({ taskList: response.data });
      });
  }

  render() {
    var objs = this.state.categoryList;
    return (
      <div>
        <h3>Category List :</h3>
        <List component="nav">
          {objs.map((obj, index) => {
            return (
              <ListItem
                button
                key={obj.id}
                onClick={event => this.categoryDidSelected(obj.id, event)}
              >
                <ListItemText primary={obj.name} />
              </ListItem>
            );
          })}
        </List>

        <CreateCategory parentContext={this} />
      </div>
    );
  }
}

export default Leftmenu;
