import { Table } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import { useAppDispatch } from '../../redux/store';
import { selectBots } from '../../redux/user/selectors';
import css from './Bots.module.scss';
import Bot from './components/bot/Bot';

export default function Bots() {
  const dispatch = useAppDispatch();
  const bots = useSelector(selectBots);

  return (
    <>
      <Table bordered hover>
        <thead>
          <tr>
            <th>#</th>
            <th>Name</th>
            <th>Balance</th>
            <th>Status</th>
            <th className={css.actions}>Actions</th>
          </tr>
        </thead>
        <tbody>
          {bots.map((value, index) => (
            <Bot bot={value} key={index} index={index} />
          ))}
        </tbody>
      </Table>
    </>
  );
}
