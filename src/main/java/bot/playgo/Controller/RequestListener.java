package bot.playgo.Controller;

import bot.playgo.Entity.Request;
import bot.playgo.PlaygoApplication;
import bot.playgo.Pojo.Response.ValidateResponse;
import bot.playgo.Pojo.ResponsePojo;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestListener {

    @GetMapping("/trx/{playerId}/{denom}/{trxId}")
    private String processRequest(
            @PathVariable String playerId,
            @PathVariable String denom,
            @PathVariable String trxId)
    {
        Request request = Request.ofParams(playerId, denom, trxId);
        printRequest(request);

        TransactionController transactionController = TransactionController.ofRequest(request);
        ResponsePojo transactionResult = transactionController.getResponsePojo();
        return new JSONObject(transactionResult).toString();
    }

    private void printRequest(Request request) {
        PlaygoApplication.logger.info("======Incoming Request===== trx/"+request.mergedRequestSeparatedBySlash()+"/");
    }

}
