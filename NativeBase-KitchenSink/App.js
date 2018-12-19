import React from "react";
import Setup from "./src/boot/setup";

export default class App extends React.Component {
  constructor() {
    super();
    this.state = {
      jwt: '',
      team_id: ''
    }
    this.newJWT = this.newJWT.bind(this);
    this.newTeamId = this.newTeamId.bind(this);

  }

  newJWT(jwt){
    this.setState({
      jwt: jwt
    });
  }

  newTeamId(teamId){
    this.setState({
      team_id: teamId
    });
  }
  render() {
    return <Setup />;
  }
}
