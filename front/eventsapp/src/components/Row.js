import React from "react";
import Card from "@material-ui/core/Card";
import CardActionArea from "@material-ui/core/CardActionArea";
import CardContent from "@material-ui/core/CardContent";
import Typography from "@material-ui/core/Typography";
import CardActions from "@material-ui/core/CardActions";
import Button from "@material-ui/core/Button";
import Collapse from "@material-ui/core/Collapse";
import {makeStyles} from "@material-ui/core/styles";

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
export function Row({item, onAdd, onRemove, myEvents}) {
    const classes = useStyles();
    var dateStart = new Date(item.startDate);
    var dateEnd = new Date(item.endDate);
    const [expanded, setExpanded] = React.useState(false);

    const handleExpandClick = () => {
        setExpanded(!expanded);
    };
    const isIn = myEvents.includes(+item.id);
    return (
        <Card className={classes.card}>
            <CardActionArea onClick={handleExpandClick}>
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
            </CardActionArea>
            <Collapse in={expanded} timeout="auto" unmountOnExit>
                <CardContent>
                    <Typography>
                        {item.description}
                    </Typography>
                    <CardActions style={{justifyContent: 'center'}}>
                        {isIn ?
                            <Button size="medium" color="primary" onClick={onRemove}>Выйти</Button> :
                            <Button size="medium" color="primary" onClick={onAdd}>Я в деле!</Button>
                        }
                    </CardActions>
                </CardContent>
            </Collapse>
        </Card>
    );
}
