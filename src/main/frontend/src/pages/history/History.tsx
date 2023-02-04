import { Table } from 'react-bootstrap';
import ColoredCircle from '../roulette/components/historyBar/coloredCircle/ColoredCircle';

export default function History() {
  const data: historyProps[] = [
    {
      id: 1,
      seed: 'askhgd2367sdfkg',
      result: 12,
      rollDate: '23:36 - 13.07.2022'
    },
    {
      id: 2,
      seed: 'sdaf45ynymfghjgfhj',
      result: 0,
      rollDate: '23:37 - 13.07.2022'
    },
    {
      id: 3,
      seed: '234bsdfbn757ghfj',
      result: 23,
      rollDate: '23:38 - 13.07.2022'
    }
  ];

  return (
    <Table striped bordered hover>
      <thead>
        <tr>
          <th>Id</th>
          <th>Seed</th>
          <th>Result</th>
          <th>Roll date</th>
        </tr>
      </thead>
      <tbody>
        {data.map((value, index) => (
          <tr key={index}>
            <td>{value.id}</td>
            <td>{value.seed}</td>
            <td>
              <ColoredCircle rolledNumber={value.result} />
            </td>
            <td>{value.rollDate}</td>
          </tr>
        ))}
      </tbody>
    </Table>
  );
}

export interface historyProps {
  id: number;
  seed: string;
  result: number;
  rollDate: string;
}
