import { ReactElement } from 'react';
import { useSelector } from 'react-redux';
import { selectRole } from '../../../redux/user/selectors';
import RedirectHome from '../redirectHome/RedirectHome';

export default function ProtectedElement(props: protectedElementProps): ReactElement {
  const userRole = useSelector(selectRole);

  if (userRole === props.role) {
    return props.children;
  }

  return props.redirectHome ? <RedirectHome /> : <></>;
}

interface protectedElementProps {
  role: string;
  children: ReactElement;
  redirectHome?: boolean;
}

ProtectedElement.defaultProps = {
  role: 'ADMIN',
  redirectHome: false
};
