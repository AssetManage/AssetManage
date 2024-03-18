import "./BankDetail.css";

const BankDetail = () => {
    return (
        <div className="bg-box-parent">
            <div className="bg-box11">
                <div className="s-logo-wrapper">
                    <div className="s-logo">
                        <img className="shinhan-ico-icon3" alt="" src="/shinhanico@2x.png"/>
                    </div>
                    <div className="s-tit6">
                        <div className="div47">신한저축은행</div>
                    </div>
                </div>
                <div className="parent2">
                    <b className="b23">더드림정기예금</b>
                    <div className="div54">
                        회전주기(매1년)마다 영업점 방문 없이 변동금리로 자동 회전되는 장기
                        목돈굴리기 상품
                    </div>
                </div>
                <div className="group-parent">
                    <div className="group-parent0">
                        <img className="group-child" alt="" src="/group-15.svg"/>
                        <div className="div49">
                            <b>6~36</b>
                            <span className="span74">개월</span>
                        </div>
                        <div className="div48">가입기간</div>
                    </div>
                    <div className="group-parent1">
                        <img className="group-item" alt="" src="/group-14.svg"/>
                        <div className="div51">
                            <span>
                              <b>10</b>
                            </span>
                            <span className="span75">
                              <span>만원</span>
                              <span className="span76"> 이상</span>
                            </span>
                        </div>
                        <div className="div50">가입금액</div>
                    </div>
                    <div className="group-parent2">
                        <img className="group-interest" alt="" src="/group-8.svg"/>
                        <div className="high_interest_section">
                            <b className="b21">연</b>
                            <b className="b22">3.7%</b>
                            <div className="div53">(세전)</div>
                        </div>
                        <div className="div52">최고금리</div>
                    </div>
                </div>
            </div>


        </div>
    );
};

export default BankDetail;
