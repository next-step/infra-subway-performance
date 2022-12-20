import task from '../task.js'
export let options = {
  stages: [
    { duration: '30s', target: 20 },
    { duration: '30s', target: 40 },
    { duration: '30s', target: 80 },
    { duration: '30s', target: 120 },
    { duration: '5m', target: 250 },
    { duration: '5m', target: 400 },
    { duration: '5m', target: 600 },
    { duration: '5m', target: 800 },
    { duration: '5m', target: 1000 },
    { duration: '10m', target: 1200 },
    { duration: '10m', target: 1400 },
    { duration: '10m', target: 1600 },
    { duration: '5m', target: 1200 },
    { duration: '5m', target: 800 },
    { duration: '1m', target: 600 },
    { duration: '30s', target: 400 },
    { duration: '30s', target: 300 },
    { duration: '30s', target: 200 },
    { duration: '30s', target: 100 },
    { duration: '30s', target: 0 },
  ],
  thresholds: {
    http_req_duration: ['p(99)<200'], // 99% of requests must complete below 200ms
  },
};



export default function ()  {
  task();
};

