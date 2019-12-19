import React from 'react';
import logo from './logo.svg';
import './App.css';

import {db} from "./index";
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import CardActionArea from '@material-ui/core/CardActionArea';
import IconButton from "@material-ui/core/IconButton";
import clsx from 'clsx';
import Collapse from "@material-ui/core/Collapse";
const useStyles = makeStyles({
  card: {
    minWidth: 275,
  },
  bullet: {
    display: 'inline-block',
    margin: '0 2px',
    transform: 'scale(0.8)',
  },
  title: {
    fontSize: 14,
  },
  pos: {
    marginBottom: 12,
  },
});

export function Row({item}) {
  const classes = useStyles();
  const bull = <span className={classes.bullet}>•</span>;
  var dateStart = new Date(item.startDate);
  var dateEnd = new Date(item.endDate);
  const [expanded, setExpanded] = React.useState(false);

  const handleExpandClick = () => {
    setExpanded(!expanded);
  };
  return (
      <Card className={classes.card}>
        <CardActionArea>
        <CardContent>
          <Typography variant="h5" component="h2">
            {item.name}
          </Typography>
          <Typography>
            {dateStart.toLocaleTimeString()}-{dateEnd.toLocaleTimeString()}
          </Typography>
          <Typography>{dateStart.toLocaleDateString()}</Typography>
          <Typography>{item.maxPersons} places of {item.maxPersons} available</Typography>
          <Typography>{item.minPersons} persons required</Typography>
        </CardContent>
        <CardActions>
          <Button size="small">I wanna go!</Button>
        </CardActions>
        </CardActionArea>
        <IconButton
            className={clsx(classes.expand, {
              [classes.expandOpen]: expanded,
            })}
            onClick={handleExpandClick}
            aria-expanded={expanded}
            aria-label="show more"
        >Show information
        </IconButton>
        <Collapse in={expanded} timeout="auto" unmountOnExit>
          <CardContent>
            <Typography>
              {item.description}
            </Typography>
          </CardContent>
        </Collapse>
      </Card>
  );
}


class App extends React.Component {

  state = {
    data: [],
  };

  componentDidMount() {
    const data = [];
    db.collection("events").get().then((querySnapshot) => {
      querySnapshot.forEach((doc) => {
        data.push(doc.data())
      });
      this.setState({data});
    });
  }

  render() {
    return <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <div>
          Space307 & DataDuck Events
          {this.state.data.map(item => (
              <Row item = {item}/>
          ))}

        </div>
        <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  }
}

export default App;
