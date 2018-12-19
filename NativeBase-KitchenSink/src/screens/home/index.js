import React, { Component } from "react";
import { ImageBackground, View, StatusBar } from "react-native";
import {
  Container,
  Button,
  H3,
  Text,
  Header,
  Left,
  Icon,
  Body,
  Title,
  Right,
  Content,
  Form,
  Item,
  Label, Input
} from "native-base";

import styles from "./styles";
import axios from 'axios';
import deviceStorage from '../../services/deviceStorage';
const launchscreenBg = require("../../../assets/launchscreen-bg.png");
const launchscreenLogo = require("../../../assets/logo-kitchen-sink.png");

class Home extends Component {
  constructor(props){
    super(props);

    this.loginUser = this.loginUser.bind(this);
    this.state = {
      email: '',
      password: ''
    };
  }

  loginUser() {

    // NOTE Post to HTTPS only in production
    axios.post("http://192.168.6.137:9001/auth/login",{
      username: this.state.email,
      password: this.state.password
    })
      .then((response) => {
        console.log(response.data);
        if(response.data.responseStatus == 0) {
          deviceStorage.saveKey("jwt", response.data.data.jwt);
          deviceStorage.saveKey("team_id", response.data.data.teamId);
          deviceStorage.saveKey("user_id", response.data.data.userId);
          this.props.jwt = response.data.data.jwt;
          this.props.team_id = response.data.data.teamId;
          // this.props.newJWT(response.data.data.jwt);
          // this.props.newTeamId(response.data.data.teamId);
          this.props.navigation.navigate("Segment");
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }
  render() {
    return (
    <Container style={styles.container}>
      <Header>
        <Left>
        </Left>
        <Body>
        <Title>Sign in to 911</Title>
        </Body>
        <Right />
      </Header>

      <Content>
        <Form>
          <Item inlineLabel>
            <Label>Username</Label>
            <Input value={this.state.email} onChangeText={(email) => this.setState({email})}/>
          </Item>
          <Item inlineLabel last>
            <Label>Password</Label>
            <Input secureTextEntry value={this.state.password} onChangeText={(password) => this.setState({password})}/>
          </Item>
        </Form>

        <Button block style={{ margin: 15, marginTop: 50 }} onPress={() => {this.loginUser()}}>
          <Text>Sign In</Text>
        </Button>
      </Content>
    </Container>
    );
  }
}

export default Home;
