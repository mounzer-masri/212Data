import React, { Component, Fragment } from "react";
import {
  GridList,
  GridListTile,
  ListSubheader,
  IconButton,
  StarBorderIcon,
  InfoIcon,
  GridListTileBar
} from "material-ui";
import { withStyles } from "material-ui";
import Leftmenu from "./Leftmenu";
import TaskList from "./TaskList";
import PropTypes from "prop-types";
const styles = theme => ({
  root: {
    flexWrap: "wrap",
    justifyContent: "space-around",
    overflow: "hidden",
    backgroundColor: theme.palette.background.paper
  },
  gridList: {
    width: 900,
    height: 650
  },
  icon: {
    color: "rgba(255, 255, 255, 0.54)"
  }
});
class WunderBody extends Component {
  state = {
    currentTaskList: [],
    selectedCategoryId: ""
  };

  constructor(props) {
    super(props);
  }

  render() {
    console.log("index");
    console.log(this);

    const { classes } = this.props;
    return (
      <div className={classes.root}>
        <GridList cols={2} className={classes.gridList}>
          <GridListTile key="" style={{ height: "auto" }}>
            <Leftmenu
              parentContext={this}
              styles={({ marginTop: 50 }, { marginBottom: 10 })}
            />
          </GridListTile>
          <GridListTile key="" style={{ height: "auto" }}>
            <TaskList parentContext={this} styles={{ marginTop: 20 }} />
          </GridListTile>
        </GridList>
      </div>
    );
  }
}
//export default WunderBody;
export default withStyles(styles)(WunderBody);
/*
TitlebarGridList.propTypes = {
  classes: PropTypes.object.isRequired
};
export default withStyles(styles)(TitlebarGridList);
*/
/**/
