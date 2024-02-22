import { useContext } from "react";
import RadioContext from "./RadioContext";

function Radio({ radioLabelStyle, children, value, name, defaultChecked, disabled }) {
	const group = useContext(RadioContext);

	return (
		<label className={radioLabelStyle}>
			<input
				type="radio"
				value={value}
				name={name}
				disabled={disabled || group.disabled}
				checked={group.value !== undefined ? value === group.value : undefined}
				onChange={(e) => group.onChange && group.onChange(e.target.value)}
			/>
			{children}
		</label>
	);
}

export default Radio;
