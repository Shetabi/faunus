import { sayHello } from '../../../services/message-service';
import './Home.css';
import { useState, useEffect } from 'react';

function Home() {

    const [message, setMessage] = useState({});

    useEffect(() => {
        sayHello().then(
            data => {
                setMessage(data.data);
                console.log(data);
            }
        )
      }, []);

  return (
    <div>
      <header className='center'>
        Got message: {message.message}
      </header>
    </div>
  );
}

export default Home;