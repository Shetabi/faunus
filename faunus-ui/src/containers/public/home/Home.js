import 'semantic-ui-css/semantic.min.css';
import { Card, Image, Icon, Header } from 'semantic-ui-react';


import { displayPlants } from '../../../services/plant-service';
import './Home.css';
import { useState, useEffect } from 'react';

import placeHolderImage from '../../../assets/images/placeholder.png'

function Home() {

    const [plants, setPlants] = useState([{}]);

    const getPlants = ()=>{
      
    }
    useEffect(() => {
      displayPlants().then(
            data => {
              setPlants(data.data);
                // console.log(JSON.stringify(data.data));
            }
        )
      }, []);

  return (
    <>
    <Header as='h1' style={{display:'flex'}} className='center'>Plant Page</Header>
    <div className='center' >
      {/* <h1 style={{display:'flex'}} className='center'> */}
      <Card >
      <Image rounded src={placeHolderImage} wrapped ui={false} />
      <Card.Content style={{background:'lightgreen'}}>
      <Card.Header>{plants[0].name}</Card.Header>
      <Card.Meta>
        <span className='date'>Created in  2023 </span>
      </Card.Meta>
      <Card.Description >
      Plant Description
      </Card.Description>
    </Card.Content>
    <Card.Content extra style={{background:'green'}}>
    <a>
        <Icon name='tree' />
        {plants.length} Plants
      </a>
    </Card.Content>
        </Card>
        
      {/* </h1> */}
    </div>
    </>
  );
}

export default Home;