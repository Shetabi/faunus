import { useEffect } from 'react';
import { useRouter } from 'next/navigation';

const withAuth = (WrappedComponent) => {
  return (props) => {
    const router = useRouter();

    useEffect(() => {
      const userId = localStorage.getItem('ownerId');
      if (!userId) {
        router.push('/login');
      }
    }, []);

    return <WrappedComponent {...props} />;
  };
};

export default withAuth;
