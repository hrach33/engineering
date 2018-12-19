import React, { Component } from "react";
import {
  Container,
  Header,
  Title,
  Content,
  Button,
  Icon,
  ListItem,
  Radio,
  Text,
  Left,
  Right,
  Body, List
} from "native-base";
import styles from "./styles";
import { AsyncStorage } from "react-native";
import axios from "axios";

class NHRadio extends Component {
  constructor(props) {
    super(props);
    this.state = {
      info: {}
    };
  }

  userId;
  jwt;

  componentDidMount = () => {
    AsyncStorage.getItem("jwt").then((j) => {
      this.jwt = j;
      AsyncStorage.getItem("user_id").then((value) => {
        this.userId = value;
        this.getPersonalInfo();
      });
    });
  };

  getPersonalInfo = () => {
    const headers = {
      "Authorization": this.jwt
    };
    axios({
      method: "GET",
      url: "http://192.168.6.137:9001/emergency/getUserById/" + this.userId,
      headers: headers
    }).then((response) => {
      this.setState({
        info: response.data.data

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
              <Icon name="menu"/>
            </Button>
          </Left>
          <Body>
          <Title>Personal info</Title>
          </Body>
          <Right/>
        </Header>

        <Content>
          <List>
            <ListItem thumbnail>
              <Body>
              <Text>
                {this.state.info.userName}
              </Text>
              <Text note numberOfLines={1}>
                username
              </Text>

              </Body>
            </ListItem>

            <ListItem thumbnail>
              <Body>
              <Text>
                {this.state.info.firstName}
              </Text>
              <Text note numberOfLines={1}>
                first name
              </Text>

              </Body>
            </ListItem>

            <ListItem thumbnail>
              <Body>
              <Text>
                {this.state.info.lastName}
              </Text>
              <Text note numberOfLines={1}>
                last name
              </Text>

              </Body>
            </ListItem>

            <ListItem thumbnail>
              <Body>
              <Text>
                {this.state.info.speciality}
              </Text>
              <Text note numberOfLines={1}>
                speciality
              </Text>

              </Body>
            </ListItem>

            <ListItem thumbnail>
              <Body>
              <Text>
                {this.state.info.status}
              </Text>
              <Text note numberOfLines={1}>
                status
              </Text>

              </Body>
            </ListItem>
          </List>
        </Content>
      </Container>
    );
  }
}

export default NHRadio;
