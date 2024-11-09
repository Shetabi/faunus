import { useEffect } from 'react';
import { useRouter } from 'next/navigation';

const withAuth = (WrappedComponent) => {
  // Set the display name for the wrapped component for easier debugging
  const WithAuthComponent = (props) => {
    const router = useRouter();

    useEffect(() => {
      const userId = localStorage.getItem('ownerId');
      if (!userId) {
        router.push('/login');
      }
    }, [router]);

    return <WrappedComponent {...props} />;
  };

  // Add a display name for the HOC
  WithAuthComponent.displayName = `WithAuth(${WrappedComponent.displayName || WrappedComponent.name || 'Component'})`;

  return WithAuthComponent;
};

export default withAuth;
