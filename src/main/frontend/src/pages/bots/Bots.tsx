import { useState } from 'react';
import { Button, Table } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import { useAppDispatch } from '../../redux/store';
import { selectBots } from '../../redux/user/selectors';
import css from './Bots.module.scss';
import AddBotModal from './components/addBotModal/AddBotModal';
import Bot from './components/bot/Bot';

export default function Bots() {
  const dispatch = useAppDispatch();
  const bots = useSelector(selectBots);

  const [showAddBotModal, setAddBotModal] = useState(false);
  const handleAddBotModalShow = () => setAddBotModal(true);
  const handleAddBotModalClose = () => setAddBotModal(false);

  return (
    <>
      <div className="m-2 float-end">
        <Button variant="success" size="sm" onClick={handleAddBotModalShow}>
          Add new
        </Button>
      </div>

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
      <AddBotModal show={showAddBotModal} handleClose={handleAddBotModalClose} />
    </>
  );
}
