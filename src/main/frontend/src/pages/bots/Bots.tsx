import { useEffect } from 'react';
import { Table } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import { fetchBots } from '../../redux/actions/botsActions';
import { selectBots } from '../../redux/reducers/botsReducer';
import { useAppDispatch } from '../../redux/store';
import Bot from './components/bot/Bot';

export default function Bots() {
  const dispatch = useAppDispatch();
  const bots = useSelector(selectBots);

  useEffect(() => {
    dispatch(fetchBots());
  }, []);

  return (
    <Table bordered hover>
      <thead>
        <tr>
          <th>#</th>
          <th>Name</th>
          <th>Balance</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        {bots.map((value, index) => (
          <Bot bot={value} key={index} />
        ))}
      </tbody>
    </Table>
  );
}
