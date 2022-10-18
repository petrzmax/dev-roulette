import { Table } from 'react-bootstrap';

export default function Ranking() {
  const data: rankingProps[] = [
    {
      username: 'tester 1',
      botname: 'Doubler',
      score: 500,
      wins: 56,
      totalBets: 120,
      firstRollId: 12,
      lastRollId: 1211
    },
    {
      username: 'tester 2',
      botname: 'Analizator',
      score: 600,
      wins: 45,
      totalBets: 23,
      firstRollId: 43,
      lastRollId: 34535
    },
    {
      username: 'tester 3',
      botname: 'IA',
      score: 23,
      wins: 43,
      totalBets: 234,
      firstRollId: 54,
      lastRollId: 23445
    }
  ];

  const column = [
    { title: '#', field: 'position' },
    { title: 'Username', field: 'username' }
  ];

  return (
    <Table striped bordered hover>
      <thead>
        <tr>
          <th>#</th>
          <th>Username</th>
          <th>Bot name</th>
          <th>Score</th>
          <th>Status</th>
          <th>Wins</th>
          <th>Total bets</th>
          <th>First roll id</th>
          <th>Last roll id</th>
        </tr>
      </thead>
      <tbody>
        {data.map((value, index) => (
          <tr key={index}>
            <td>{index + 1}</td>
            <td>{value.username}</td>
            <td>{value.botname}</td>
            <td>{value.score}</td>
            <td>{value.wins}</td>
            <td>{value.totalBets}</td>
            <td>{value.firstRollId}</td>
            <td>{value.lastRollId}</td>
          </tr>
        ))}
      </tbody>
    </Table>
  );
}

export interface rankingProps {
  username: string;
  botname: string;
  score: number;
  wins: number;
  totalBets: number;
  firstRollId: number;
  lastRollId: number;
}
