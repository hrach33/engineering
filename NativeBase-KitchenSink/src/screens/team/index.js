import React, { Component } from "react";
import {
  Container,
  Header,
  Button,
  Icon,
  Item,
  Input,
  Content,
  Text, Left, Body, Title, Right, ListItem, List
} from "native-base";
import styles from "./styles";
import axios from "axios";
import deviceStorage from "../../services/deviceStorage";
import { AsyncStorage } from "react-native";
import * as ListView from "react-native/Libraries/Lists/ListView/ListView";

class NHSearchbar extends Component {
  constructor(props) {
    super(props);
    this.state = {
      users: []
    };
  }

  teamId;
  jwt;

  componentDidMount = () => {
    AsyncStorage.getItem("jwt").then((j) => {
      this.jwt = j;
      AsyncStorage.getItem("team_id").then((value) => {
        this.teamId = value;
        this.getUsers();
      });
    });
  }

  getUsers = () => {
    const headers = {
      "Authorization": this.jwt
    };
    axios({
      method: "GET",
      url: "http://192.168.6.137:9001/emergency/getUsersByTeamId/" + this.teamId,
      headers: headers
    }).then((response) => {
          this.setState({
            users: response.data.data

          });
        })
        .catch((error) => {
          console.log(error);
        });

  };


  render() {
    return (
      <Container style={styles.container}>
        <Header>
          <Left>
            <Button
              transparent
              onPress={() => this.props.navigation.navigate("DrawerOpen")}
            >
              <Icon name="menu" />
            </Button>
          </Left>
          <Body>
          <Title>Team members</Title>
          </Body>
          <Right />
        </Header>

        <Content padder>
          <List>
            {this.state.users.map((data) =>(
              <ListItem key={data.id} thumbnail>
                <Body>
                <Text>
                  {data.firstName + ' ' + data.lastName}
                </Text>
                <Text note numberOfLines={1}>
                  Speciality: {data.speciality}
                </Text>
                </Body>

              </ListItem>))}
          </List>

        </Content>
      </Container>
    );
  }
}

export default NHSearchbar;
