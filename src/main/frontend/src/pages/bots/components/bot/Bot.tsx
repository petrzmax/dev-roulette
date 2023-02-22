import { FormCheck } from 'react-bootstrap';
import { MdDelete, MdEditNote, MdPlayArrow } from 'react-icons/md';
import { BotDto } from '../../../../redux/user/reducer';

export default function Bot(props: BotProps) {
  return (
    <tr>
      <td>
        <FormCheck />
      </td>
      <td>{props.bot.name}</td>
      <td>{props.bot.balance}</td>
      <td>
        <MdPlayArrow />
        <MdEditNote />
        <MdDelete />
      </td>
    </tr>
  );
}

interface BotProps {
  bot: BotDto;
}
