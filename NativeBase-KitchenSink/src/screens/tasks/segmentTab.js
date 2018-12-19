import React, { Component } from "react";
import {
  Container,
  Header,
  Title,
  Content,
  Text,
  Button,
  Icon,
  Left,
  Right,
  Body,
  Segment, CardItem, Card, ListItem, List
} from "native-base";
import axios from "axios";
import deviceStorage from "../../services/deviceStorage";
import { AsyncStorage } from "react-native";
import styles from "../card/styles";
import * as ListView from "react-native/Libraries/Lists/ListView/ListView";

class SegmentNB extends Component {
  constructor(props) {
    super(props);
    this.state = {
      seg: 2,
      pending: [],
      inProgress: [],
      finished: []
    };
  }

  componentDidMount = () => {
    this.call = true;
    this.timer = setInterval(() => {
      if (this.call) {
        this.getTasks();
      }
    }, 2000);
  };

  componentWillUnmount = () => {
    this.call = false;
  };

  getTasks = () => {
    AsyncStorage.getItem("team_id").then((value) => {
      console.log("http://192.168.6.137:9001/emergency/getTasksByTeamId/" + value);
      // NOTE Post to HTTPS only in production
      axios.get("http://192.168.6.137:9001/emergency/getTasksByTeamId/" + value)
        .then((response) => {
          const temp1 = _.filter(response.data.data, { state: "pending" });
          const temp2 = _.filter(response.data.data, { state: "in_progress" });
          const temp3 = _.filter(response.data.data, { state: "finished" });
          console.log(temp3);
          this.setState({
            pending: temp1,
            inProgress: temp2,
            finished: temp3

          });
        })
        .catch((error) => {
          console.log(error);
        });
    });

  };

  take(taskId) {
    axios.post("http://192.168.6.137:9001/emergency/takeTask", {
      id: taskId
    })
      .then((response) => {
        console.log(response.data);
        this.render();
      })
      .catch((error) => {
        console.log(error);
      });
  }

  updateStatus(taskId, status) {
    axios.post("http://192.168.6.137:9001/emergency/finishTask", {
      id: taskId,
      status: status
    })
      .then((response) => {
        console.log(response.data);
        this.render();
      })
      .catch((error) => {
        console.log(error);
      });
  }

  render() {
    return (
      <Container>
        <Header hasTabs>
          <Left>
            <Button transparent onPress={() => this.props.navigation.goBack()}>
              <Icon name="arrow-back"/>
            </Button>
          </Left>
          <Body>
          <Title>Tasks</Title>
          </Body>
          <Right>
            <Button transparent>
              <Icon name="search"/>
            </Button>
          </Right>
        </Header>
        <Segment>
          <Button
            first
            active={this.state.seg === 1 ? true : false}
            onPress={() => this.setState({ seg: 1 })}
          >
            <Text>Waiting</Text>
          </Button>
          <Button
            active={this.state.seg === 2 ? true : false}
            onPress={() => this.setState({ seg: 2 })}
          >
            <Text>In progress</Text>
          </Button>
          <Button
            last
            active={this.state.seg === 3 ? true : false}
            onPress={() => this.setState({ seg: 3 })}
          >
            <Text>Finished</Text>
          </Button>
        </Segment>

        <Content padder>
          {this.state.seg === 1 &&

            <List>
              {this.state.pending.map((data) =>(
                <ListItem key={data.id} thumbnail>
                  <Body>
                  <Text>
                    {data.type}
                  </Text>
                  <Text note numberOfLines={1}>
                    Destination: {data.destination}
                  </Text>
                  <Text note numberOfLines={1}>
                    Description: {data.description}
                  </Text>
                  </Body>
                  <Right>
                    <Button transparent onPress={() => this.take(data.id)}>
                      <Text>take</Text>
                    </Button>

                  </Right>
                </ListItem>))}
            </List>

          }
          {this.state.seg === 2 &&

            <List>
              {this.state.inProgress.map((data) =>(
                <ListItem key={data.id} thumbnail>
                  <Body>
                  <Text>
                    {data.type}
                  </Text>
                  <Text note numberOfLines={1}>
                    Destination: {data.destination}
                  </Text>
                  <Text note numberOfLines={1}>
                    Description: {data.description}
                  </Text>
                  </Body>
                  <Right>
                    <Button transparent onPress={() => this.updateStatus(data.id, "success")}>
                      <Text>done</Text>
                    </Button>
                    <Button transparent onPress={() => this.updateStatus(data.id, "issue")}>
                      <Text>issue</Text>
                    </Button>
                  </Right>
                </ListItem>))}
            </List>

          }
          {this.state.seg === 3 &&

            <List>
              {this.state.finished.map((data) =>(
                <ListItem key={data.id} thumbnail>
                  <Body>
                  <Text>
                    {data.type}
                  </Text>
                  <Text note numberOfLines={1}>
                    Destination: {data.destination}
                  </Text>
                  <Text note numberOfLines={1}>
                    Description: {data.description}
                  </Text>
                  <Text note numberOfLines={1}>
                    Status: {data.status}
                  </Text>
                  </Body>
                </ListItem>))}
            </List>

          }
        </Content>
      </Container>
    );
  }
}

export default SegmentNB;
