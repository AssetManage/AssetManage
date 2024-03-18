import React from 'react';
import { getMonthDate, getHourMinutes } from '@utils/date';
import './styles.css'; // 혹은 해당 컴포넌트에 필요한 스타일 시트를 직접 작성하여 import할 수 있습니다.

import ScheduleIcon from '@assets/schedule.svg';
import CancelIcon from '@assets/cancel.svg';

const TableRow = ({
                      id,
                      status,
                      isHiddenSheet,
                      name,
                      image,
                      dateTime,
                      statusName,
                      color,
                      bgColor,
                      onClickSheet,
                      onClickCancel,
                  }) => {
    const date = getMonthDate(dateTime);
    const time = getHourMinutes(dateTime);
    const isEditStatus = status === 'BEFORE_APPROVED' || status === 'APPROVED';

    return (
        <tr>
            <td>
        <span className="status" style={{ backgroundColor: bgColor, color: color }}>
          {statusName}
        </span>
            </td>
            <td>
                <div className="profile">
                    <img src={image} alt={`${name} 프로필 이미지`} />
                    <span>{name}</span>
                </div>
            </td>
            <td>{date}</td>
            <td>{time}</td>
            <td>
                {!isHiddenSheet && <img src={ScheduleIcon} alt="스케줄 아이콘" onClick={onClickSheet} />}
                {isEditStatus && (
                    <img src={CancelIcon} alt="취소 아이콘" onClick={() => onClickCancel?.(id)} />
                )}
            </td>
        </tr>
    );
};

export default TableRow;
