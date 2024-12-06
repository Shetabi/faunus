/** @type {import('next').NextConfig} */
import withPWA from 'next-pwa';


const config = {
    typescript: {
      ignoreBuildErrors: true,
    }
};

const nextConfig = withPWA({
  dest: 'public',
})(
  config
);

export default nextConfig;
