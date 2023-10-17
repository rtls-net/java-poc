List<String> lgcygrpList =  Arrays.asList(caseNum);
			List<CompletableFuture<DeleteGroupResponse>> futureList =  new ArrayList<>();
			lgcygrpList.forEach(str->{
				

				CompletableFuture<DeleteGroupResponse> responseComplte = CompletableFuture.supplyAsync(() -> {
                      try {
						return  service.unlockAndDeleteByLgcyGrpNbr(caseNum, userId, "LG");
					}catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    return null;

				});
				futureList.add(responseComplte);
				
			});
			
			response = futureList.get(0).get();
			CompletableFuture<Void> finalCompletableFuture = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));
			finalCompletableFuture.join();
