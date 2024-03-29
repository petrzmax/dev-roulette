import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import eslint from 'vite-plugin-eslint';
import viteTsconfigPaths from 'vite-tsconfig-paths';

export default defineConfig(() => {
  return {
    server: {
      open: true,
      port: 3000

    },
    build: {
      outDir: 'build'
    },
    plugins: [react(), viteTsconfigPaths(), eslint()]
  };
});
