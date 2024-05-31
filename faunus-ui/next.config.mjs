/** @type {import('next').NextConfig} */
import withPWA from 'next-pwa';


const config = {
};

const nextConfig = withPWA({
  dest: 'public',
})(
  config
);

export default nextConfig;
