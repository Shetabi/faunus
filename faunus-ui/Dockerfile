# Use an official Node.js LTS version 14 as the base image
FROM node:20-alpine AS builder

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY package.json package-lock.json ./

# Install dependencies
RUN npm install --production

# Copy the rest of the application code
COPY . .

# Install Tailwind CSS and other necessary files
RUN npm install tailwindcss postcss autoprefixer

# Build the Next.js application
RUN npm run build

# Use a lightweight Node.js LTS version 14 as the base image for the production environment
FROM node:20-alpine AS production

# Set the working directory in the container
WORKDIR /app

# Copy the built application from the previous stage
COPY --from=builder /app/.next ./.next
COPY --from=builder /app/public ./public
COPY --from=builder /app/package.json ./package.json
COPY --from=builder /app/node_modules ./node_modules
COPY --from=builder /app/tailwind.config.ts ./tailwind.config.ts

# Expose the port that the Next.js application runs on
EXPOSE 3000

# Start the Next.js application
CMD ["npm", "start"]
