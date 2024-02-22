import RadioContext from "./RadioContext";

function RadioGroup({ className, children, ...rest }) {
	return (
		<fieldset className={className}>
			<RadioContext.Provider value={rest}>{children}</RadioContext.Provider>
		</fieldset>
	);
}

export default RadioGroup;
