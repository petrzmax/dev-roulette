import { createAction } from '@reduxjs/toolkit';
import AdminActionTypes from './actionTypes';
import { MessageDto } from './admin.model';

export const dispatchMessage = createAction<MessageDto>(AdminActionTypes.DISPATCH_MESSAGE);
