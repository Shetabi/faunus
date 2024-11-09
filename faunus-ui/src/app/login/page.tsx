"use client";

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import { Input, Card, Button} from '@material-tailwind/react';
import LoginService from "src/repositories/LoginService";

export default function LoginPage() {
  const [username, setUsername] = useState('');
  const router = useRouter();

  const handleLogin = async () => {
      try {
        const loginService = new LoginService();
        console.log(username);
        const response = await loginService.login(username);
        localStorage.setItem('ownerId', response.toString());
        router.push('/');
      }
      catch (error) {
        alert('ورود ناموفق بود');
      }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100 bg-primary-dark">
      <Card
        sx={{
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          padding: 10,
          borderRadius: 2,
          boxShadow: 3,
        }}
        noValidate
        autoComplete="off"
        className="p-10 bg-primary-dark-lighter"
      >
        <h1 className="text-2xl font-bold mb-4 text-white">ورود</h1>
        <Input
          label="نام کاربری"
          variant="outlined"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          size="gl"
          color="white"
          className="text-white"
        />
        <Button variant="contained" className="mt-4 mb-4 bg-accent" onClick={handleLogin}>
          ورود
        </Button>
      </Card>
    </div>
  );
}

