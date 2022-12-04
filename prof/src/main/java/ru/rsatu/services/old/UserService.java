package ru.rsatu.services.old;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService {

//    /**
//     * Покупка
//     * @return
//     */
//    public BaseResponse buyProgram(BuyProgramRequest request, String username) {
//        StudyProgram program = StudyProgram.findById(request.studyProgramId);
//        Contract contract = new Contract(jwt.getSubject(), username, program, false);
//        contract.persist();
//        return new BaseResponse("ok", "Покупка");
//    }

//    /**
//     * Получение всех программ
//     * @return
//     */
//    public BaseResponse programsByUser() {
//        Query query = em.createQuery("select new ru.rsatu.old.POJO.userPOJO.program.StudyProgramByUser(" +
//                "study.studyProgramId, study.name, study.description,\n" +
//                "                 c.isComplete) from StudyProgram study\n" +
//                "                left join Contract c on study.studyProgramId = c.studyProgram.studyProgramId \n" +
//                "                where  c.userID like :userid ");
//        query.setParameter("userid", jwt.getSubject());
//        List<StudyProgramByUser> list = query.getResultList();
//        System.out.println(list.size());
//        return new ProgramsByUserResponse(list);
//    }
}
