

@Tag(name = "ConsumptionType", description = "소비성향분석")
@Controller
@RequestMapping("st/cnsmp_incln")
public class ConsumptionType {

    private ConsumptionType consumptionType;

    public ConsumptionTypeController(ConsumptionType consumptionType){
        this.consumptionType = consumptionType;
    }

    @Operation(summary = "소비성향 조회", description = "소비성향을 조회한다.")
    @GetMapping("/getConsumptionType")
    public List<Map<String, Object>> getConsumptionType(@RequestParam("cnsmp_incln_cd") String consumptionType){
        List<Map<String, Object>> consumptionType = consumptionTypeService.getConsumptionType(cnsmp_incln_cd);
        return consumptionType;
    }

    @Operation(summary = "소비성향 정의", description = "소비성향을 정의한다.")
    @GetMapping("/getConsumptionTypeDefine")
    public void getConsumptionTypeDefine(){

    }



}