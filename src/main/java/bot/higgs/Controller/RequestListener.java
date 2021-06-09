package bot.higgs.Controller;

import bot.higgs.Entity.Request;
import bot.higgs.HiggsDominoApplication;
import bot.higgs.Pojo.ResponsePojo;
import bot.higgs.Utility.Components;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class RequestListener {

    @GetMapping("/trx/{playerId}/{denom}/{trxId}")
    private String processRequest(
            @PathVariable String playerId,
            @PathVariable String denom,
            @PathVariable String trxId) {

        Request request = Request.ofParams(playerId, denom, trxId);

        printRequest(request);
        setRequestTimestamp();

        TransactionController transactionController = TransactionController.ofRequest(request);
        ResponsePojo transactionResult = transactionController.getResponsePojo();
        return new JSONObject(transactionResult).toString();
    }

    private void setRequestTimestamp() {
        try {
            Components.formattedRequestTimestamp = new SimpleDateFormat(Components.dateFormatPattern).parse(new Timestamp(new Date().getTime()).toString());
            HiggsDominoApplication.logger.info("Set Timestamp -> " +Components.formattedRequestTimestamp);
        } catch (Exception e) {
            HiggsDominoApplication.logger.info(e.getMessage());
        }
    }

    private void printRequest(Request request) {
        HiggsDominoApplication.logger.info("====== Incoming Request ===== trx/" + request.mergedRequestSeparatedBySlash() + "/");
    }

}
